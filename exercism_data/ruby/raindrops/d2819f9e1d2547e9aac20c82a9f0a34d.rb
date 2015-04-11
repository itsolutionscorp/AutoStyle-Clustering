# It is just copy of @brightbits at http://exercism.io/submissions/2002126ea0f04ee2b61e6daaaef48ed5

class Raindrops
  FACTORS = {
    3 => "Pling",
    5 => "Plang",
    7 => "Plong"
  }

  def self.convert(x)
    results = FACTORS.map { |f, r| r if x % f == 0 }.compact
    results.empty? ? x.to_s : results.join
  end
end
