class Atbash
  PLAIN = ('a'..'z').to_a
  CIPHER = PLAIN.reverse
  class << self
    def encode(plaintext)
      ciphertext = character_array(plaintext).map { |c| encode_char(c) }.join
      space_cipertext(ciphertext)
    end

    private

    def character_array(plaintext)
      plaintext.downcase.each_char
    end

    def encode_char(c)
      cipher_index = PLAIN.index(c)
      cipher_index ? CIPHER[cipher_index] : c =~ /\d/ ? c : ''
    end

    def space_cipertext(ciphertext)
      ciphertext.chars.each_slice(5).map(&:join).join(' ')
    end
  end
end
