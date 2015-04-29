module Atbash
  ALPHABET = ("a".."z").to_a.join

  def self.encode(text)
    text.
      gsub(/\W+/, "").
      downcase.
      tr(ALPHABET, ALPHABET.reverse).
      scan(/.{1,5}/).join(" ")
  end
end
