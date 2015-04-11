# Inspired by http://exercism.io/submissions/2953f3fb54b347f8cb53df49
class Atbash
  PLAIN = ('a'..'z').to_a.join
  CIPHER = PLAIN.reverse

  def initialize(string)
    @string = string
  end

  def self.encode(string)
    new(string).chunked(5)
  end

  def chunked(length)
    cipher = cleaned_chars.tr(PLAIN, CIPHER)
    cipher.chars.each_slice(length).map(&:join).join(' ')
  end

  private

  def cleaned_chars
    @string.downcase.gsub(/\W/, '')
  end
end
