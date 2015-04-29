(defn hey [what]
   (setv answer "Whatever.")
   (setv which (.strip what))
   (cond [(.isupper which) (setv answer "Whoa, chill out!")] 
         [(.endswith which "?") (setv answer "Sure.")]
         [(not (.strip which)) (setv answer "Fine. Be that way!")]
         )
   answer
   )
