class Raindrops
  class << self
    def convert(i)
      str =  ''
      str += 'Pling' if i % 3 == 0
      str += 'Plang' if i % 5 == 0
      str += 'Plong' if i % 7 == 0

      str.empty? ? i.to_s : str
    end
  end
end
