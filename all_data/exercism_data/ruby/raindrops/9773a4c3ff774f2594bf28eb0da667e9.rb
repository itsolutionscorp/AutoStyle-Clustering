class Raindrops
  class << self
    def convert(arg)
      "".tap do |str|
        str << "Pling" if arg % 3 == 0
        str << "Plang" if arg % 5 == 0
        str << "Plong" if arg % 7 == 0
        str << "#{arg}" if str.empty?
      end
    end
  end

end
