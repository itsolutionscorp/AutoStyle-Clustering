class Raindrops

  class << self
    def convert(num)
      out = ''
      out << 'Pling' if num % 3 == 0
      out << 'Plang' if num % 5 == 0
      out << 'Plong' if num % 7 == 0
      out.empty? ? num.to_s : out
    end
  end
end
