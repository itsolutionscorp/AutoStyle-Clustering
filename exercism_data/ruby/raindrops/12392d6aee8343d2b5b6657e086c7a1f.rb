class Raindrops
  def self.convert(num)
    content = ''
    content << 'Pling' if num % 3 == 0
    content << 'Plang' if num % 5 == 0
    content << 'Plong' if num % 7 == 0
    content.empty? ? num.to_s : content
  end
end
