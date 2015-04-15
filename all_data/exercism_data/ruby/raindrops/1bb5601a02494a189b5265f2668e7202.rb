class Raindrops
  def self.convert number
    factors_list = factors number
    converted = ""
    converted << 'Pling' if factors_list.include? 3
    converted << 'Plang' if factors_list.include? 5
    converted << 'Plong' if factors_list.include? 7
    converted = number.to_s if converted == ""
    converted
  end

  def self.factors number
    (1..(Math.sqrt(number)+1)).inject([]) do |f,e|
      f += [e, number/e] if (number % e) == 0
      f
    end.sort.uniq
  end
end
