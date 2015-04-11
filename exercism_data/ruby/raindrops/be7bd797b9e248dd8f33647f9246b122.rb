# I really favor my first solution,
# but writing this was fun!
require 'prime'
module Raindrops
  def self.convert n
    s = ([3,5,7] & n.prime_division.map(&:first)).join
    s.empty? ? n.to_s : s.gsub(/[357]/,{'3'=>'Pling','5'=>'Plang','7'=>'Plong'})
  end
end
