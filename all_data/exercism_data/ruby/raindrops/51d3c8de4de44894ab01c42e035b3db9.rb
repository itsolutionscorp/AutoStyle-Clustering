class Raindrops
  @subs = {3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}

  def self.convert(arg)
    str = [3, 5, 7].collect do |num|
      (arg % num).zero? ? @subs[num] : ""
    end.join("")
    str.empty? ? arg.to_s : str
  end
end
