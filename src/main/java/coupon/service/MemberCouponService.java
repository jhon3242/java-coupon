package coupon.service;

import coupon.domain.Coupon;
import coupon.domain.Member;
import coupon.domain.MemberCoupon;
import coupon.domain.repository.MemberCouponRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MemberCouponService {

    private static final int MAX_SAME_COUPON_SIZE = 5;

    private MemberCouponRepository memberCouponRepository;

    public MemberCouponService(MemberCouponRepository memberCouponRepository) {
        this.memberCouponRepository = memberCouponRepository;
    }

    public MemberCoupon create(Member member, Coupon coupon) {
        int sameCouponSize = memberCouponRepository.countMemberCouponByMemberIdAndCouponId(member.getId(), coupon.getId());
        if (sameCouponSize > MAX_SAME_COUPON_SIZE) {
            throw new IllegalArgumentException("Same coupon size exceeded");
        }
        MemberCoupon issue = MemberCoupon.issue(member.getId(), coupon.getId(), coupon.getIssueStartDate());
        return memberCouponRepository.save(issue);
    }

    public List<MemberCoupon> findAllByMemberId(Long memberId) {
        return memberCouponRepository.findAllByMemberId(memberId);
    }
}
