# Hamming code class
class Hamming
  def self.compute( left_str, right_str )
    left_str.chars.zip( right_str.chars ).reduce( 0 ) do |a, (left, right)|
      right && left != right ? a + 1 : a
    end
  end
end
