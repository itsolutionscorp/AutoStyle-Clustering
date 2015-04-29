class Raindrops
  def self.convert(argh)
    converted = ''
    converted += 'Pling' if argh % 3 == 0
    converted += 'Plang' if argh % 5 == 0
    converted += 'Plong' if argh % 7 == 0
    converted = argh.to_s if converted.empty?
    converted 
  end
end
