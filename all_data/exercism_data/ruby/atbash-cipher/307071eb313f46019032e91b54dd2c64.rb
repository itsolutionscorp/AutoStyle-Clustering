module Atbash
  CYPHER = Hash[*("a".."z").zip(("a".."z").to_a.reverse).flatten]
  def self.encode(string)
    words = [""]
    string.downcase.gsub(/[\W]/, "").each_char do |letter|
      word = words.last
      if word.length == 5
        words << ""
        word = words.last
      end
      word << (CYPHER[letter] || letter)
    end
    words.join ' '
  end
end
