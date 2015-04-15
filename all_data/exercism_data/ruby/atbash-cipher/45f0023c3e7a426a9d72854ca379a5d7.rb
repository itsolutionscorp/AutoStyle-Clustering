class Atbash
  class << self
    def encode(plain_text)
      cypher_text_chars = plain_text.gsub(/[^a-zA-Z0-9]/, '').downcase.chars.map{ |char| encode_character(char) }
      result = []
      cypher_text_chars.each_slice(5){|char_slice| result << char_slice.join}
      result.join(' ')
    end

    private
    def encode_character(char)
      alphabet_index = char.ord - 'a'.ord
      /[0-9]/.match(char) ? char :  ('a'.ord + (25 - alphabet_index)).chr
    end
  end
end
