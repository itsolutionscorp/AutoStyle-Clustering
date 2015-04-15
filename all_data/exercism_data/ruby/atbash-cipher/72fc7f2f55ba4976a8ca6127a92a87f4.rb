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

  def blockformat(text)
    #txt_blocks = text.split(/(.....)/)
    #txt_blocks.join(' ').gsub('  ',' ').lstrip

    text.scan(/...../).join(' ')
  end
end
