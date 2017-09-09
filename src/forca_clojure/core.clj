(ns forca-clojure.core
  (:gen-class))

(def total-de-vidas 6)
(def palavra-secreta "CLOJURE")

(defn perdeu [] (print "Você perdeu! :("))

(defn ganhou [] (print "Você ganhou! :)"))

(defn letrasFaltantes [palavra acertos]
	(remove (fn [letra] (contains? acertos (str letra))) palavra))

(defn acertouPalavraInteira? [palavra acertos]
	(empty? (letrasFaltantes palavra acertos)))

(defn obtemChute! [] (read-line))

(defn acertou? [chute palavra] (.contains palavra chute))

(defn imprimeDetalhes [vidas palavra acertos]
	(println "Vidas: " vidas)
	(doseq [letra (seq palavra)]
		(if (contains? acertos (str letra))
			(print letra "")
			(print "_ ")))
	(println))

(defn jogo [vidas palavra acertos]
	(imprimeDetalhes vidas palavra acertos)
	(cond
		(= vidas 0) (perdeu)
		(acertouPalavraInteira? palavra acertos) (ganhou)
		:else
		(let [chute (obtemChute!)]
			(if (acertou? chute palavra)
				(do
					(println "Acertou a letra!")
					(recur vidas palavra (conj acertos chute))
				)
				(do
					(println "Errou a letra e perdeu uma vida!")
					(recur (dec vidas) palavra acertos))))))

(defn comecarJogo [] (jogo total-de-vidas palavra-secreta #{}))

(defn -main [& args] (comecarJogo))
