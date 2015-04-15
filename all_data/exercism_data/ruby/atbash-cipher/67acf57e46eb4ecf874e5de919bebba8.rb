class Atbash
  def self.encode(text)
    new(text).encode
  end


  attr_reader :text
  def initialize(text)
    @text = text
  end

  def encode
    cipher = ''
    text.downcase.each_char do |ch|
      code = encoding[ch]
      cipher << code if code
    end
    format cipher
  end

private
  def encoding
    coding = {' ' => ''}
    coding.merge! alpha_map
    coding.merge! numeric_map
  end

  def alpha_map
    alphabet.each_with_index.inject({}) do |h, (ch,i)|
      h.merge( {ch => alphabet[-(i+1)]} )
    end
  end

  def numeric_map
    ('0'..'9').inject({}) {|h,ch| h.merge({ch => ch}) }
  end

  def alphabet
    ('a'..'z').inject([]) {|a,ch| a << ch }
  end

  def format(text)
    chrs = text.chars
    txt_blocks = []
    five = chrs.shift(5)
    until five.empty? do
      txt_blocks << five.join
      five = chrs.shift(5)
    end
    txt_blocks.join(' ')
  end
end
