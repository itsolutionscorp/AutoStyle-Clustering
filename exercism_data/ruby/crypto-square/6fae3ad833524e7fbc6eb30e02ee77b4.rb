class Crypto
  def initialize(plaintext)
    @plaintext = plaintext
  end

  def ciphertext
    pivot(plaintext_segments)
  end

  def normalize_plaintext
    @plaintext.downcase.gsub(/\W+/, '')
  end

  def normalize_ciphertext
    segment(ciphertext, 5).join(' ')
  end

  def plaintext_segments
    segment(normalize_plaintext, size)
  end

  def size
    Math.sqrt(normalize_plaintext.length).ceil
  end

  private

    def pivot(stringArray)
      stringArray.each_with_object([]) { |s, charArray|
        charArray << s.ljust(size, ' ').split('')
      }.transpose.each_with_object('') { |chars, result|
        result << chars.join('').strip
      }
    end

    def segment(s, segment_size)
      s.scan(Regexp.new(".{1," + segment_size.to_s + "}"))
    end
end
