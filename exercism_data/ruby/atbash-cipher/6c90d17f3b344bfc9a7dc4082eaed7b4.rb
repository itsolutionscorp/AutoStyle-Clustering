class String
  def to_a
    chars
  end

  def split(chunk_size)
    scan(/.{1,#{chunk_size}}/)
  end
end

class Atbash
  class << self
    def encode(letters)
      str = letters.to_a.collect { |letter| encode_letter(letter) }.join
      split = str.split(5)
      split.join(' ')
    end

    def encode_letter(letter)
      letter_lookup_hash[letter.downcase]
    end

    def letter_lookup_hash
      @letters ||= Hash[('a'..'z').to_a.zip( ('a'..'z').to_a.reverse )]
      @numbers ||= Hash[(0..9).to_a.map(&:to_s).zip( (0..9).to_a )]
      @letters.merge(@numbers)
    end
  end
end
