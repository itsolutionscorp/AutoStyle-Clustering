class Atbash

ALPHA = ('a'..'z').to_a + ('0'..'9').to_a
CIPER = ('a'..'z').to_a.reverse + ('0'..'9').to_a

PLAIN = Hash[ALPHA.zip(CIPER)]

  def self.encode(input_string)
    characters = input_string.downcase.gsub(/[^a-z0-9]+/,'').chars
    characters.map do |char|
      PLAIN[char]
    end.join.scan(/.{1,5}/).join(' ')
  end

end
