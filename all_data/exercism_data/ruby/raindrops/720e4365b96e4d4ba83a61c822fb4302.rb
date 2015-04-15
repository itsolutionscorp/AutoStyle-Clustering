class Raindrops
  @@fragments = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }

  def self.convert(n)
    s = @@fragments.inject('') { |acc, (i, fragment)|
      acc + ((n % i == 0) ? fragment : '')
    }

    # Return the original digits if nothing matched
    s.empty? ?  n.to_s : s
  end
end
