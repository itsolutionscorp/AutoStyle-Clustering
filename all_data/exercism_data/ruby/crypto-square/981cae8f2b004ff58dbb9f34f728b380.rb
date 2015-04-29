class Crypto
  CIPHERTXT_SEG_LEN = 5
  attr_reader :plaintext
  def initialize(plaintext)
    @plaintext = clean(plaintext)
  end

  def ciphertext
    ct = ''
    squaresize.times do |i|
      ct << plaintext_segments.inject('') do |column_string,segment|
              column_string << (segment[i] || '')
            end
    end
    ct
  end 

  def normalize_ciphertext
    segment(ciphertext, CIPHERTXT_SEG_LEN).join(' ')
  end

private
  def clean(text)
    text.downcase.gsub(/[^a-z0-9]/,'')
  end

  def squaresize
    sq = Math.sqrt(plaintext.size).ceil
  end

  def plaintext_segments
    segment(plaintext, squaresize)
  end

  def segment(text, segmentlength)
    #pattern = Regexp.new(".{1,#{segmentlength}}")
    text.scan(/.{1,#{segmentlength}}/)
  end
end
