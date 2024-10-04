package com.github.juanncode.challengeapuestatotal.mappers

import com.github.juanncode.challengeapuestatotal.domain.entity.BBSelection
import com.github.juanncode.challengeapuestatotal.domain.entity.Bet
import com.github.juanncode.challengeapuestatotal.domain.entity.BetDetail
import com.github.juanncode.challengeapuestatotal.domain.entity.BetSelection
import com.github.juanncode.challengeapuestatotal.domain.entity.User
import com.github.juanncode.challengeapuestatotal.retrofit.model.BBSelectionModel
import com.github.juanncode.challengeapuestatotal.retrofit.model.BetDetailModel
import com.github.juanncode.challengeapuestatotal.retrofit.model.BetModel
import com.github.juanncode.challengeapuestatotal.retrofit.model.BetSelectionModel
import com.github.juanncode.challengeapuestatotal.retrofit.model.UserModel


fun UserModel.toDomain() = User(
    name, correo, password
)

fun BetModel.toDomain() = Bet(
    db, operation, game, createdDate = created_date, status, wager, winning, odds, type, account
)

fun BetDetailModel.toDomain() = BetDetail(
    BetId = BetId,
    BetNivel = BetNivel,
    BetSelections = BetSelections?.map { it.toDomain() } ?: emptyList(),
    BetStarts = BetStarts,
    BetStatus = BetStatus,
    BetStatusName = BetStatusName,
    BetType = BetType,
    BetTypeName = BetTypeName,
    BgSrc = BgSrc,
    CashoutOdds = CashoutOdds,
    CashoutValue = CashoutValue,
    CreatedDate = CreatedDate,
    TotalOdds = TotalOdds,
    TotalStake = TotalStake,
    TotalWin = TotalWin,
)

fun BetSelectionModel.toDomain() = BetSelection(
    BBSelections = BBSelections?.map { it.toDomain() } ?: emptyList(),
    BoreDraw = BoreDraw,
    CategoryId = CategoryId,
    CategoryName = CategoryName ?: "",
    ChampId = ChampId,
    ChampName = ChampName ?: "",
    DbId = DbId,
    DeadHeatFactor = DeadHeatFactor ?: "",
    EarlyPayout = EarlyPayout,
    EventCode = EventCode ?: "",
    EventDate = EventDate,
    EventId = EventId,
    EventName = EventName,
    EventScore = EventScore ?: "",
    ExtraEventInfo = ExtraEventInfo,
    FeedEventId = FeedEventId,
    GameTime = GameTime ?: "",
    IsBanker = IsBanker,
    IsBetBuilder = IsBetBuilder,
    IsLive = IsLive,
    IsLiveOrVirtual = IsLiveOrVirtual,
    IsVirtual = IsVirtual,
    LiveInfoAtEventMinute = LiveInfoAtEventMinute,
    MarketId = MarketId,
    MarketName = MarketName,
    MarketTypeId = MarketTypeId,
    Name = Name,
    PitcherInfo = PitcherInfo,
    Price = Price,
    RC = RC,
    Runners = Runners,
    SelectionId = SelectionId,
    SelectionStatus = SelectionStatus,
    Spec = Spec ?: "",
    SportTypeId = SportTypeId,
)

fun BBSelectionModel.toDomain() = BBSelection(
    BoreDraw, EarlyPayout, MarketName, SelectionId, SelectionName, SelectionStatus
)