class Raindrops
  def self.convert(int)
    output = (1..int).select { |n| (int % n).zero? }
    unless output.any? { |o| [3,5,7].include?(o) }
      int.to_s
    else
      output.keep_if { |o|  [3,5,7].include?(o) }.join.gsub(/[357]/, "3" => "Pling", "5" => "Plang", "7" => "Plong" )
    end
  end
end
