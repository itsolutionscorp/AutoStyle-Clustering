class Crypto
  CIPHERTXT_SEG_LEN = 5
  PADCHAR = ' '
  attr_reader :plaintext
  def initialize(plaintext)
    @plaintext = clean(plaintext)
    pad_plaintext
  end

  def ciphertext
    ptxt_array = plaintext_segments.map {|seg| seg.chars }
    ptxt_array.transpose.join.gsub(' ','')
  end 

  def normalize_ciphertext
    segment(ciphertext, CIPHERTXT_SEG_LEN).join(' ')
  end

private
  def clean(text)
    text.downcase.gsub(/[^a-z0-9]/,'')
  end

  def squaresize
    Math.sqrt(plaintext.size).ceil
  end

  def plaintext_segments
    segment(plaintext, squaresize)
  end

  def segment(text, segmentlength)
    text.scan(/.{1,#{segmentlength}}/)
  end

  def pad_plaintext
    plaintext << padding
  end

  def padding
    PADCHAR * (squaresize**2 - plaintext.size)
  end
end

# Changed test
#  def test_can_segment_plaintext
#    skip
#    crypto = Crypto.new('Never vex thine heart with idle woes')
#    segments = ["neverv", "exthin", "eheart", "withid", "lewoes", "      "]
#    assert_equal segments, crypto.send(:plaintext_segments)
#  end
