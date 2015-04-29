class Scrabble

  def initialize string
    @string = (string || "").strip
  end

  def self.score string
    new(string).score
  end

  def score
    @string.chars.uniq.each.inject(0) do |sum, letter|
      sum + @string.count(letter) * self.class.scores[letter.upcase]
    end
  end

  private
    def self.scores
      @scores ||= begin
        plain_scores = <<-EOS
          A, E, I, O, U, L, N, R, S, T       1
          D, G                               2
          B, C, M, P                         3
          F, H, V, W, Y                      4
          K                                  5
          J, X                               8
          Q, Z                               10
        EOS
        plain_scores.each_line.inject({}) do |mapping, line|
          letters, score = line.split(/\s+(\d+)/)
          letters.split(',').each.inject(mapping) do |m, letter|
            m[letter.strip] = score.to_i; m
          end
        end
      end
    end
end
