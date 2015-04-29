module Raindrops
  module_function

  def convert(i)
    {
      3 => "Pling",
      5 => "Plang",
      7 => "Plong",
    }.map do |n, output|
      output if i % n == 0
    end.compact.join("").tap do |s|
      return i.to_s if s.empty?
    end
  end
end
