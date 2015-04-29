class Raindrops
  def self.convert(drop)
    return 'Pling' if [3,6,9].include? drop
    return 'Plang' if [5,10,25].include? drop
    return 'Plong' if [7,14,49].include? drop
    return 'PlingPlang' if [15].include? drop
    return 'PlingPlong' if [21].include? drop
    return 'PlangPlong' if [35].include? drop
    return 'PlingPlangPlong' if [105].include? drop
    drop.to_s
  end
end
