class Raindrops
  LYRICS = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }

  def self.convert(n)
    hits = find_primes(n) & [3, 5, 7]
    hits.empty? ? n.to_s : hits.map { |e| LYRICS[e] }.join('')
  end

  private

    def self.find_primes(n)
      [1, 2].include?(n) ? [] : (2..n).to_a.select { |d| n % d == 0 }
    end
end
