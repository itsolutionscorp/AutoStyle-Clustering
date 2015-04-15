class Atbash
  REVERSE_ALPHABET = ("a".."z").to_a.reverse.join.freeze

  def self.encode(text)
    text.downcase.gsub(/\W/, "").tr('a-z', REVERSE_ALPHABET).scan(/.{1,5}/).join(" ")
  end
end
