# version 1
class Raindrops
  def self.convert(nr)
    result = [3, 5, 7]
      .inject('') { |str, i| str << (nr % i == 0 ? "Pl#{i}ng" : '') }
      .tr('357','iao')
    result.empty? ? nr.to_s : result
  end
end

# version 2
class Raindrops
  def self.convert(nr)
    nr = nr.to_s.extend(MDiv)
    nr.string.empty? ? nr.to_s : nr.string 
  end

  module MDiv
    def d3
      'Pling' if self.to_i % 3 == 0
    end

    def d5
      'Plang' if self.to_i % 5 == 0
    end

    def d7
      'Plong' if self.to_i % 7 == 0
    end

    def string
      "#{self.d3}#{self.d5}#{self.d7}"
    end
  end

end
