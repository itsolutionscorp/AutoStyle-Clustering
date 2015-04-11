class Bob
  def hey(word)
     if word.length < 1 || word[0] == " " || word.gsub(/[\t]/, "*") != word
      "Fine. Be that way!"
     elsif word == word.upcase && word.gsub(/[a-zA-Z]/, "*") != word
      "Whoa, chill out!"
     elsif word[-1] != "?"
       "Whatever."
     elsif word[-1] == "?"
      "Sure."
     end
  end
end
