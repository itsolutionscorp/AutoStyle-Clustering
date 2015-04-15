class Raindrops
  def convert(number)
    lookup = {
      105 => "PlingPlangPlong",
      35  => "PlangPlong",
      21  => "PlingPlong",
      15  => "PlingPlang",
      7   => "Plong",
      5   => "Plang",
      3   => "Pling"
    }
    divisor = lookup.keys.detect { |k| number % k == 0 }
    divisor ? lookup[divisor] : number.to_s
  end
end
