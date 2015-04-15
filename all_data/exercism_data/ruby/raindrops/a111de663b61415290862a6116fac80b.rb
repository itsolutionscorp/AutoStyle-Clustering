class Raindrops
  FRAGMENTS = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }

  def self.convert(n)
    s = FRAGMENTS.inject('') do |acc, (i, fragment)|
      acc << ((n % i == 0) ? fragment : '')
    end

    # Return the original digits if nothing matched
    s.empty? ?  n.to_s : s
  end
end
