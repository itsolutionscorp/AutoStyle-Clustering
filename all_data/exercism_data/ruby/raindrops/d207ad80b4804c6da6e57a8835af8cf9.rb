class Raindrops
  def self.convert(v)
    result = ''
    { 3 => 'i', 5 => 'a', 7 => 'o' }.each do |f, c|
      result << "Pl#{c}ng" if v % f == 0
    end
    result == '' ? v.to_s : result
  end
end
