class Atbash
  class << self

    def encode plain
      chunkate plain.downcase.chars.map { |char| cipher[char] }.join
    end

    private

    def chunkate cipher_text
      cipher_text.scan(/.{1,5}/).join(' ')
    end

    def cipher
      @cipher ||= Hash[plain_key.zip(cipher_value)]
    end

    def plain_key
      ('a'..'z').to_a + ('0'..'9').to_a
    end

    def cipher_value
      ('a'..'z').to_a.reverse + ('0'..'9').to_a
    end
  end
end
