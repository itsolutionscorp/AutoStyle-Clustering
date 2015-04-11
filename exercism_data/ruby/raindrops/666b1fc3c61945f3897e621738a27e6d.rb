class Raindrops
  def convert(qty)
    output = ""
    test_factors.each { |prime, sound| output << sound if qty % prime == 0 }
    output.empty? ? qty.to_s : output
  end

  def test_factors
    {
      3 => "Pling",
      5 => "Plang",
      7 => "Plong"
    }
  end
end
