class Bob
	class ReplicaFactory
		PARSERS = [
			->(statement) {'Fine. Be that way!' if statement.nil? || statement.strip.empty?},
			->(statement) {'Woah, chill out!' if statement == statement.upcase},
			->(statement) {'Sure.' if statement.end_with?('?')},
			->(statement) {'Whatever.'}
		]

		def self.reply(statement)
			ReplicaFactory.new(statement).reply
		end

		attr_reader :statement

		def initialize(statement)
			@statement = statement
		end

		def reply
			PARSERS.each do |p|
				replica = p.call(@statement)
				break(replica) if replica
			end
		end
	end

	def hey(message)
		ReplicaFactory.reply(message)		
	end
end
