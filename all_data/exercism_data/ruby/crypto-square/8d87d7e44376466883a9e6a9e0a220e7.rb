class Crypto
  def initialize(plaintext)
    @plaintext = plaintext
  end

  def normalize_plaintext
    @plaintext.gsub(/\W/,'').downcase
  end

  def normalize_ciphertext
    segment(ciphertext, 5).join(' ')
  end

  def plaintext_segments
    segment(normalize_plaintext, size)
  end

  def ciphertext
    result = ""
    (0..size).each do |index|
      plaintext_segments.each do |segment|
        result << segment[index] if segment[index]
      end
    end
    result
  end

  def size
    Math.sqrt(normalize_plaintext.size).ceil
  end

  private

  def segment(ary, seg_length)
    segments = []
    index = 0
    while index < ary.size
      segments << ary[index..index+seg_length-1]
      index += seg_length
    end
    segments
  end

end
