module Raindrops
  def self.convert n
    if has_factor? n, 105
      'PlingPlangPlong'
    elsif has_factor? n, 15
      'PlingPlang'
    elsif has_factor? n, 21
      'PlingPlong'
    elsif has_factor? n, 35
      'PlangPlong'
    elsif has_factor? n, 3
      'Pling'
    elsif has_factor? n, 5
      'Plang'
    elsif has_factor? n, 7
      'Plong'
    else
      n.to_s
    end
  end

  private

  def self.has_factor? x, y
    x % y == 0
  end
end
