class Atbash
  class << self
    def encode(string)
      cleaned = cleanup(string)
      encoded = cleaned.chars.map { |c| cipher(c) }
      format(encoded)
    end

    def format(array)
      array.each_slice(5).map do |slice|
        slice.join
      end.join(" ")
    end

    def cleanup(string)
      string.downcase.gsub(/\W/, '')
    end

    def cipher(character)
      @cipher_map ||= Hash[('a'..'z').zip(('a'..'z').to_a.reverse)]
      @cipher_map[character] || character
    end
  end
end
