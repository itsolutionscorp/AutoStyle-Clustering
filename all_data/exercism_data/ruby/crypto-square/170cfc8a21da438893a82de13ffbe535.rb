class Crypto

  def initialize(string)
    @string = string
  end

  def normalize_plaintext
    @string.gsub(/[\W]/,'').downcase
  end

  def size
    (normalize_plaintext.length ** 0.5).ceil
  end

  def plaintext_segments
    normalize_plaintext.scan(/.{1,#{size}}/)
  end

  def ciphertext
    results = []
    idx = 0
      while idx < size
        plaintext_segments.map do |segment|
          results << segment[idx]
        end
      idx += 1
    end
    results.join
  end

  def normalize_ciphertext
    ciphertext.scan(/.{1,#{size}}/).join(' ')
  end

end
