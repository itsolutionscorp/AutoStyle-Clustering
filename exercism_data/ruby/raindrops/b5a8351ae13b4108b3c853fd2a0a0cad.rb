class Raindrops
  def self.convert(number)
    factors = (1..number).select { |n| (number % n).zero? }
    i = ""
    case
    when factors.include?(3) && factors.include?(5) && factors.include?(7)
       i << "PlingPlangPlong"
    when factors.include?(3) && factors.include?(5)
       i << "PlingPlang"
    when factors.include?(3) && factors.include?(7)
       i << "PlingPlong"
   when factors.include?(5) && factors.include?(7)
       i << "PlangPlong"
    when factors.include?(3)
      i << "Pling"
    when factors.include?(5)
      i << "Plang"
    when factors.include?(7)
      i << "Plong"
    else
        i << number.to_s
    end
    i
    end
end
