module Raindrops
  FACTOR_MAP = {
    3 => "Pling",
    5 => "Plang",
    7 => "Plong"
  }.freeze

  module_function
  def convert( n )
    result = FACTOR_MAP.reduce( "" ) do |s, pair|
      factor, output = pair
      if n % factor == 0
        s << output
      else
        s
      end
    end

    return n.to_s if result.empty?
    result
  end
end
