module Raindrops
  module_function

  def convert n
    res = ''
    res << 'Pling' if 0 == n % 3
    res << 'Plang' if 0 == n % 5
    res << 'Plong' if 0 == n % 7
    res.empty?? n.to_s : res
  end
end
