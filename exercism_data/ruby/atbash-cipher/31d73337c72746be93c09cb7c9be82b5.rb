class Atbash
  def self.encode message
    # encoding is a simple linear mapping on `ord`
    chars = message.gsub(/\W+/, '').downcase.chars
    encoded = chars.map { |c| c.between?('a', 'z') ? (219 - c.ord).chr : c }
    (0..chars.length-1).step(5).map { |i| encoded[i, 5].join }.join ' '
  end
end
