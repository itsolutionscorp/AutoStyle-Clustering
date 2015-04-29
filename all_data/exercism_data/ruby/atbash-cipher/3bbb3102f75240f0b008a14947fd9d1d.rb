class Atbash

  AZ = ('a'..'z').to_a

  def self.encode(text)    
    format encrypt clean text
  end

  def self.clean(text)
    text.downcase.gsub(/[^a-z0-9]/, '')
  end

  def self.encrypt(text)
    text.chars.map do |c|
      c.to_i.to_s == c ? c : AZ.reverse[AZ.index(c)]
    end
  end

  def self.format(text)
    text.each_slice(5).map(&:join).join(' ')
  end

end
