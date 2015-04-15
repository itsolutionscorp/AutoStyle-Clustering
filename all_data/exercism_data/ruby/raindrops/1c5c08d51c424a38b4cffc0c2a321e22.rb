module Raindrops

  def self.convert nb
    res = CONVERT.inject('') do |acc, (key, value)|
      acc + (nb % key == 0 and value or '')
    end
    res.empty? and nb.to_s or res
  end

  private

  CONVERT = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }

end
