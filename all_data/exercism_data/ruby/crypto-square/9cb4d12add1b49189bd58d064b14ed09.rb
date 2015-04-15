class Crypto
  attr_reader :plaintext, :normalized
  def initialize(plaintext)
    @plaintext = plaintext
    @normalized = normalize_plaintext
  end

  def normalize_plaintext
    plaintext.downcase.gsub(/[^a-z0-9]/,'')
  end

  def size
    sq = Math.sqrt(normalized.size).to_i
    (sq**2 == normalized.size) ? sq : sq + 1
  end

  def plaintext_segments
    segment(normalize_plaintext, size)
  end

  def ciphertext
    ct = ''
    size.times do |i|
      ct << plaintext_segments.inject('') do |column_string,segment|
              column_string << (segment[i] || '')
            end
    end
    ct
  end 

  def normalize_ciphertext
    segment(ciphertext, 5).join(' ')
  end

  def segment(text, length)
    pattern = Regexp.new(".{1,#{length}}")
    text.scan(pattern)
  end
end
