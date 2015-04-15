class Crypto
  def initialize(s)
    @s = s
  end

  def normalize_plaintext
    @s.downcase.gsub(/[^\w]/, "")
  end

  def size
    Math.sqrt(normalize_plaintext.length).ceil
  end

  def plaintext_segments
    normalize_plaintext.scan(Regexp.new(".{1,#{size}}"))
  end

  def ciphertext
    res = ""
    (0...size).each do |i|
      plaintext_segments.each do |word|
        break if i >= word.length
        res << word[i]
      end
    end
    res
  end

  def normalize_ciphertext
    ciphertext.scan(/.{1,5}/).join(" ")
  end
end
