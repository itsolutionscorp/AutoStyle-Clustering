class Crypto
  def initialize(text)
    @text = text
  end
  attr_reader :text

  def normalize_plaintext
    text.gsub(/[^[:alnum:]]/, '').downcase
  end

  def plaintext_segments
    normalize_plaintext.chars.each_slice(size).map(&:join)
  end

  def ciphertext
    cipher = ''
    (0..(size - 1)).to_a.each do |x|
      plaintext_segments.each do |word|
        word[x] ? cipher << word[x] : next
      end
    end
    cipher
  end

  def normalize_ciphertext
    ciphertext.length % size == 0 ? split = size : split = size - 1
    ans = Regexp.new(".{1,#{split}}")
    ciphertext.scan(ans).join(' ')
  end

  def size
    total_size = normalize_plaintext.length
    square_test = Math.sqrt(total_size)
    square_test.ceil
  end

end
