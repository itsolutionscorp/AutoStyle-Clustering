class Hamming
  def self.compute(s1, s2)
    # this one liner is hard to read
    # s1.split('').map.with_index{ |a,i| a != s2[i] and !s2[i].nil? }.delete_if{ |x| x == false }.count

    # this is easier to read
=begin
    diff = 0
    s1.length.times do |i|
      if s1[i] != s2[i] and !s2[i].nil?
        diff += 1
      end
    end
    return diff
=end
    diff = 0
    [s1.length, s2.length].min.times do |i|
      if s1[i] != s2[i]
        diff += 1
      end
    end
    diff
  end
end