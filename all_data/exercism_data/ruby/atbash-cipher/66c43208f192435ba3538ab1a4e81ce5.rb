class Atbash
  def self.encode message
    # encoding is a simple linear mapping on `ord`
    chars = message.gsub(/\W/, '').downcase.chars
    encoded = chars.map { |c| c.between?('a', 'z') ? (219 - c.ord).chr : c }
    encoded.each_slice(5).map(&:join).join ' '
  end
end
