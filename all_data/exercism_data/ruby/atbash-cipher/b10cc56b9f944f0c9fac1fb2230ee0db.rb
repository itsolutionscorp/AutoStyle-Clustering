class Atbash
  LETTERS = ("a".."z").to_a
  GROUP_SIZE = 5

  def initialize(plain_text)
    @plain_text = plain_text.downcase.gsub(/\W/, "")
  end

  def encode
    plaintext_segments.map do |segment|
      cipher segment
    end.join(" ")
  end

  def self.encode(plain_text)
    new(plain_text).encode
  end

  private
  def plaintext_segments
    @plain_text.chars.each_slice(GROUP_SIZE).map { |chars| chars.join }
  end

  def cipher(text)
    text.chars.map do |char|
      if LETTERS.include? char
        idx = LETTERS.index(char) + 1
        LETTERS[-idx]
      else
        char
      end
    end.join
  end
end
