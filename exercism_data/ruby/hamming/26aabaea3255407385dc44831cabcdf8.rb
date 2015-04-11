module Hamming
  def self.compute( s1, s2 )
    zipped( s1, s2 ).count { |(a,b)| a != b && !( a.nil? || b.nil? ) }
  end

  private

  def self.zipped( s1, s2 )
    s1.chars.zip( s2.chars )
  end
end
