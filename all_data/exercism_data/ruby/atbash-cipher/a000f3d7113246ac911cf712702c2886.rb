class Atbash
  ALPHABET = ('a'..'z').inject([]) {|a,ch| a << ch }
  def self.encode(text)
    new(text).encode
  end

  attr_reader :text
  def initialize(text)
    @text = normalize text
  end

  def encode
    cipher = ''
    text.each_char do |ch|
      code = encoding[ch]
      cipher << code
    end
    blockformat cipher
  end

private
  def encoding
    coding = Hash.new('')
    coding.merge! alpha_map
    coding.merge! numeric_map
  end

  def alpha_map
    ALPHABET.each_with_index.inject({}) do |h, (ch,i)|
      h.merge( {ch => ALPHABET[-(i+1)]} )
    end
  end

  def numeric_map
    ('0'..'9').inject({}) {|h,ch| h.merge({ch => ch}) }
  end

  def blockformat(text)
    text.scan(/.{0,5}/).join(' ').rstrip
  end

  def normalize
    text.downcase.gsub(/^a..z0..9/,'')
  end
end 
